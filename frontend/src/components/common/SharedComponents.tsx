import { 
  Search, 
  User, 
  Bell,
  Settings,
  ChevronDown,
  LogOut,
  Zap,
  Code,
  GraduationCap,
  FileBarChart,
  Compass,
  Archive,
  Layers,
  Library,
  Map,
  Palette,
  LayoutTemplate,
  Briefcase,
  Plus,
  Github, 
  Twitter,
  Linkedin,
  Instagram,
  Youtube,
  Target,
  TrendingUp
} from 'lucide-react';
import { useState } from 'react';
import { Input } from '../../ui/input';
import { Button } from '../../ui/button';
import { Avatar, AvatarFallback } from '../../ui/avatar';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '../../ui/dropdown-menu';

// QuizComponent export
export { QuizComponent } from "./QuizComponent";

// ========================
// GlobalHeader Component
// ========================

interface GlobalHeaderProps {
  onNavigate: (page: string) => void;
  onLogout: () => void;
  currentView: string;
  isLoggedIn: boolean;
  isAdmin: boolean;
}

export function GlobalHeader({ onNavigate, onLogout, currentView, isLoggedIn, isAdmin }: GlobalHeaderProps) {
  const [searchQuery, setSearchQuery] = useState('');

  return (
    <header className="bg-white border-b border-[#1CB0F6]/20 sticky top-0 z-50 backdrop-blur-sm bg-white/80">
      <div className="max-w-[1920px] mx-auto px-8">
        <div className="max-w-[1440px] mx-auto h-16 flex items-center justify-between gap-8">
          {/* Left Section: Logo + Navigation */}
          <div className="flex items-center gap-8 flex-1">
            {/* Logo */}
            <button 
              onClick={() => onNavigate('home')}
              className="text-2xl font-bold text-[#1CB0F6] flex-shrink-0 tracking-tight hover:opacity-80 transition-opacity"
            >
              Trenda
            </button>

            {/* Navigation */}
            <nav className="hidden md:flex items-center gap-1">
              {/* Play */}
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <Button variant="ghost" className="text-sm font-medium text-gray-600 hover:text-[#1CB0F6] hover:bg-[#1CB0F6]/5 gap-1">
                    Play
                    <ChevronDown className="w-4 h-4 opacity-50" />
                  </Button>
                </DropdownMenuTrigger>
                <DropdownMenuContent align="start" className="w-64">
                  <DropdownMenuItem onClick={() => onNavigate('trend-flash')} className="gap-3 cursor-pointer items-start p-2">
                    <Zap className="w-4 h-4 text-[#1CB0F6] mt-0.5" />
                    <div className="flex flex-col gap-0.5">
                      <span className="font-medium">TrendFlash</span>
                      <span className="text-xs text-gray-500">트렌드 카드 게임</span>
                    </div>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onNavigate('code-flash')} className="gap-3 cursor-pointer items-start p-2">
                    <Code className="w-4 h-4 text-[#1CB0F6] mt-0.5" />
                    <div className="flex flex-col gap-0.5">
                      <span className="font-medium">CodeFlash</span>
                      <span className="text-xs text-gray-500">코드 스니펫 실력 게임</span>
                    </div>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onNavigate('design-flash')} className="gap-3 cursor-pointer items-start p-2">
                    <Palette className="w-4 h-4 text-[#1CB0F6] mt-0.5" />
                    <div className="flex flex-col gap-0.5">
                      <span className="font-medium">DesignFlash</span>
                      <span className="text-xs text-gray-500">디자인 감각 테스트</span>
                    </div>
                  </DropdownMenuItem>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={() => onNavigate('level-test')} className="gap-3 cursor-pointer items-start p-2">
                    <GraduationCap className="w-4 h-4 text-[#1CB0F6] mt-0.5" />
                    <div className="flex flex-col gap-0.5">
                      <span className="font-medium">Level Test</span>
                      <span className="text-xs text-gray-500">실력 레벨 테스트</span>
                    </div>
                  </DropdownMenuItem>
                </DropdownMenuContent>
              </DropdownMenu>

              {/* Explore */}
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <Button variant="ghost" className="text-sm font-medium text-gray-600 hover:text-[#1CB0F6] hover:bg-[#1CB0F6]/5 gap-1">
                    Explore
                    <ChevronDown className="w-4 h-4 opacity-50" />
                  </Button>
                </DropdownMenuTrigger>
                <DropdownMenuContent align="start" className="w-56">
                  <DropdownMenuItem onClick={() => onNavigate('explore-entry')} className="gap-2 cursor-pointer bg-blue-50/50">
                    <Compass className="w-4 h-4 text-[#1CB0F6]" />
                    <span className="font-bold text-[#1CB0F6]">Explore Home</span>
                  </DropdownMenuItem>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={() => onNavigate('trends')} className="gap-2 cursor-pointer">
                    <TrendingUp className="w-4 h-4 text-[#1CB0F6]" />
                    <span>Trend Research</span>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onNavigate('project-archive')} className="gap-2 cursor-pointer">
                    <Archive className="w-4 h-4 text-[#1CB0F6]" />
                    <span>Project Archive</span>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onNavigate('resources')} className="gap-2 cursor-pointer">
                    <Library className="w-4 h-4 text-[#1CB0F6]" />
                    <span>Resources</span>
                  </DropdownMenuItem>
                </DropdownMenuContent>
              </DropdownMenu>

              {/* Build */}
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <Button variant="ghost" className="text-sm font-medium text-gray-600 hover:text-[#1CB0F6] hover:bg-[#1CB0F6]/5 gap-1">
                    Build
                    <ChevronDown className="w-4 h-4 opacity-50" />
                  </Button>
                </DropdownMenuTrigger>
                <DropdownMenuContent align="start" className="w-64">
                  <DropdownMenuItem onClick={() => onNavigate('build-entry')} className="gap-3 cursor-pointer items-start p-2">
                    <Target className="w-4 h-4 text-[#1CB0F6] mt-0.5" />
                    <div className="flex flex-col gap-0.5">
                      <span className="font-medium">Build Overview</span>
                      <span className="text-xs text-gray-500">커리어 빌딩 대시보드</span>
                    </div>
                  </DropdownMenuItem>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={() => onNavigate('roadmap-generator')} className="gap-3 cursor-pointer items-start p-2">
                    <Map className="w-4 h-4 text-[#1CB0F6] mt-0.5" />
                    <div className="flex flex-col gap-0.5">
                      <span className="font-medium">Roadmap Generator</span>
                      <span className="text-xs text-gray-500">AI 커리어 로드맵</span>
                    </div>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onNavigate('portfolio-manager')} className="gap-3 cursor-pointer items-start p-2">
                    <Briefcase className="w-4 h-4 text-[#1CB0F6] mt-0.5" />
                    <div className="flex flex-col gap-0.5">
                      <span className="font-medium">Portfolio Manager</span>
                      <span className="text-xs text-gray-500">포트폴리오 관리</span>
                    </div>
                  </DropdownMenuItem>
                </DropdownMenuContent>
              </DropdownMenu>
            </nav>
          </div>

          {/* Right Section: Search + User Menu */}
          <div className="flex items-center gap-4 flex-shrink-0">
            {/* Search Bar */}
            <div className="relative hidden lg:block w-64">
              <Input 
                placeholder="검색"
                className="pl-10 pr-4 py-2 bg-[#F8F9FA] border border-[#1CB0F6]/20 rounded-full focus:border-[#1CB0F6] focus:bg-white transition-colors h-10"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
              />
              <Search className="absolute left-3.5 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />
            </div>

            {/* User Menu */}
            {isLoggedIn ? (
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <button className="w-10 h-10 rounded-full border border-[#1CB0F6]/30 bg-white flex items-center justify-center text-[#1CB0F6] hover:bg-[#1CB0F6] hover:text-white transition-colors">
                    <User className="w-5 h-5" />
                  </button>
                </DropdownMenuTrigger>
                <DropdownMenuContent align="end" className="w-56">
                  <DropdownMenuLabel>
                    <div className="flex flex-col">
                      <span className="text-sm font-semibold">User Menu</span>
                    </div>
                  </DropdownMenuLabel>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={() => onNavigate('mypage')} className="gap-2 cursor-pointer">
                    <User className="w-4 h-4" />
                    <span>My Page</span>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onNavigate('notifications')} className="gap-2 cursor-pointer">
                    <Bell className="w-4 h-4" />
                    <span>Notifications</span>
                  </DropdownMenuItem>
                  <DropdownMenuItem onClick={() => onNavigate('settings')} className="gap-2 cursor-pointer">
                    <Settings className="w-4 h-4" />
                    <span>Settings</span>
                  </DropdownMenuItem>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={onLogout} className="text-red-600 gap-2 cursor-pointer">
                    <LogOut className="w-4 h-4" />
                    <span>Logout</span>
                  </DropdownMenuItem>
                </DropdownMenuContent>
              </DropdownMenu>
            ) : (
              <Button 
                onClick={() => onNavigate('login')}
                className="bg-[#1CB0F6] hover:bg-[#1CB0F6]/90 text-white rounded-full px-6"
              >
                Sign In
              </Button>
            )}
          </div>
        </div>
      </div>
    </header>
  );
}

// ========================
// Header Component
// ========================

interface HeaderProps {
  onGoHome: () => void;
  onGoToArchive: () => void;
  onGoToPremium: () => void;
  onGoToDashboard: () => void;
  onGoToSubmit: () => void;
  onGoToAdmin: () => void;
  onGoToReport?: () => void;
  onGoToLearning?: () => void;
  onGoToCommunity?: () => void;
  onGoToSkillTest?: () => void;
  onGoToProjects?: () => void;
  onGoToContest?: () => void;
  onGoToPortfolio?: () => void;
  onNavigate: (page: string) => void;
  onLogout: () => void;
  currentView: string;
  isLoggedIn: boolean;
  isAdmin: boolean;
}

export function Header({ onGoHome, onGoToArchive, onGoToPremium, onGoToDashboard, onGoToSubmit, onGoToAdmin, onGoToReport, onGoToLearning, onGoToCommunity, onGoToSkillTest, onGoToProjects, onGoToContest, onGoToPortfolio, onNavigate, onLogout, currentView, isLoggedIn, isAdmin }: HeaderProps) {
  return (
    <header className="sticky top-0 z-50 bg-white border-b border-gray-200">
      <div className="max-w-[1440px] mx-auto px-8 py-3">
        <div className="flex items-center justify-between gap-8">
          {/* Logo Button */}
          <button 
            onClick={onGoHome}
            className="px-6 py-2.5 border-2 border-gray-900 rounded-lg text-gray-900 hover:bg-gray-900 hover:text-white transition-colors"
            style={{ fontSize: '0.9375rem', fontWeight: '500' }}
          >
            Trenda
          </button>

          {/* Search Bar */}
          <div className="flex-1 max-w-md">
            <div className="relative">
              <Input 
                placeholder="검색" 
                className="pl-4 pr-12 py-2.5 bg-gray-50 border-gray-300 rounded-lg focus:border-[#007AFF]"
              />
              <button className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-500 hover:text-gray-900">
                <Search className="w-5 h-5" />
              </button>
            </div>
          </div>

          {/* Navigation Tabs */}
          <nav className="flex items-center gap-1">
            <button 
              onClick={onGoToArchive}
              className={`px-5 py-2.5 rounded-lg transition-colors ${
                currentView === 'archive' || currentView === 'detail'
                  ? 'bg-gray-100 text-gray-900' 
                  : 'text-gray-600 hover:bg-gray-50 hover:text-gray-900'
              }`}
              style={{ fontSize: '0.9375rem', fontWeight: '500' }}
            >
              Archive
            </button>
            <button 
              onClick={onGoToReport}
              className={`px-5 py-2.5 rounded-lg transition-colors ${
                currentView === 'report' 
                  ? 'bg-gray-100 text-gray-900' 
                  : 'text-gray-600 hover:bg-gray-50 hover:text-gray-900'
              }`}
              style={{ fontSize: '0.9375rem', fontWeight: '500' }}
            >
              Report
            </button>
            <button 
              onClick={onGoToLearning}
              className={`px-5 py-2.5 rounded-lg transition-colors ${
                currentView === 'learning' 
                  ? 'bg-gray-100 text-gray-900' 
                  : 'text-gray-600 hover:bg-gray-50 hover:text-gray-900'
              }`}
              style={{ fontSize: '0.9375rem', fontWeight: '500' }}
            >
              Learning
            </button>
            <button 
              onClick={onGoToCommunity}
              className={`px-5 py-2.5 rounded-lg transition-colors ${
                currentView === 'community' 
                  ? 'bg-gray-100 text-gray-900' 
                  : 'text-gray-600 hover:bg-gray-50 hover:text-gray-900'
              }`}
              style={{ fontSize: '0.9375rem', fontWeight: '500' }}
            >
              Community
            </button>
          </nav>

          {/* Right Actions */}
          <div className="flex items-center gap-3">
            {/* Add Button */}
            {isLoggedIn && !isAdmin && (
              <button
                onClick={onGoToSubmit}
                className="w-10 h-10 rounded-full border-2 border-gray-900 flex items-center justify-center text-gray-900 hover:bg-gray-900 hover:text-white transition-colors"
              >
                <Plus className="w-5 h-5" />
              </button>
            )}

            {/* Settings Button */}
            <button className="w-10 h-10 rounded-full border-2 border-gray-900 flex items-center justify-center text-gray-900 hover:bg-gray-900 hover:text-white transition-colors">
              <Settings className="w-5 h-5" />
            </button>

            {/* Profile Dropdown */}
            {isLoggedIn ? (
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <button className="w-10 h-10 rounded-full border-2 border-gray-900 flex items-center justify-center text-gray-900 hover:bg-gray-900 hover:text-white transition-colors">
                    <User className="w-5 h-5" />
                  </button>
                </DropdownMenuTrigger>
                <DropdownMenuContent align="end" className="w-48">
                  <DropdownMenuLabel>내 계정</DropdownMenuLabel>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={onGoToDashboard}>
                    <User className="w-4 h-4 mr-2" />
                    대시보드
                  </DropdownMenuItem>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={onGoToPremium}>
                    프리미엄 구독
                  </DropdownMenuItem>
                  {isAdmin && (
                    <DropdownMenuItem onClick={onGoToAdmin}>
                      관리자 페이지
                    </DropdownMenuItem>
                  )}
                  {!isAdmin && (
                    <DropdownMenuItem onClick={onGoToSubmit}>
                      <Plus className="w-4 h-4 mr-2" />
                      트렌드 등록
                    </DropdownMenuItem>
                  )}
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={onLogout}>
                    로그아웃
                  </DropdownMenuItem>
                </DropdownMenuContent>
              </DropdownMenu>
            ) : (
              <button 
                onClick={() => onNavigate('login')}
                className="w-10 h-10 rounded-full border-2 border-gray-900 flex items-center justify-center text-gray-900 hover:bg-gray-900 hover:text-white transition-colors"
              >
                <User className="w-5 h-5" />
              </button>
            )}
          </div>
        </div>
      </div>
    </header>
  );
}

// ========================
// Footer Component
// ========================

export function Footer() {
  return (
    <footer className="bg-white border-t border-[#1CB0F6]/20 mt-24">
      <div className="max-w-[1920px] mx-auto px-8 py-16">
        <div className="max-w-[1440px] mx-auto">
          <div className="grid grid-cols-1 md:grid-cols-12 gap-12 mb-12">
            {/* Branding Section (3 cols) */}
            <div className="md:col-span-3 space-y-6">
              <div>
                <h2 className="text-2xl font-bold text-[#1CB0F6] tracking-tight">Trenda</h2>
                <p className="mt-2 text-sm text-gray-600 leading-relaxed">
                  Trend Archive Platform<br />
                  for Developers & Designers.
                </p>
              </div>
              <div className="flex gap-3">
                <a href="#" className="w-8 h-8 rounded-full bg-[#1CB0F6]/10 flex items-center justify-center text-[#1CB0F6] hover:bg-[#1CB0F6] hover:text-white transition-colors">
                  <Twitter className="w-4 h-4" />
                </a>
                <a href="#" className="w-8 h-8 rounded-full bg-[#1CB0F6]/10 flex items-center justify-center text-[#1CB0F6] hover:bg-[#1CB0F6] hover:text-white transition-colors">
                  <Instagram className="w-4 h-4" />
                </a>
                <a href="#" className="w-8 h-8 rounded-full bg-[#1CB0F6]/10 flex items-center justify-center text-[#1CB0F6] hover:bg-[#1CB0F6] hover:text-white transition-colors">
                  <Github className="w-4 h-4" />
                </a>
              </div>
            </div>
            
            {/* Info (3 cols) */}
            <div className="md:col-span-3">
              <h3 className="font-semibold text-gray-900 mb-4">Info</h3>
              <ul className="space-y-3 text-sm text-gray-600">
                <li><a href="#" className="hover:text-[#1CB0F6] transition-colors">About</a></li>
                <li><a href="#" className="hover:text-[#1CB0F6] transition-colors">Contact</a></li>
                <li><a href="#" className="hover:text-[#1CB0F6] transition-colors">Version Notes</a></li>
              </ul>
            </div>

            {/* Resources (3 cols) */}
            <div className="md:col-span-3">
              <h3 className="font-semibold text-gray-900 mb-4">Resources</h3>
              <ul className="space-y-3 text-sm text-gray-600">
                <li><a href="#" className="hover:text-[#1CB0F6] transition-colors">UI/UX 자료</a></li>
                <li><a href="#" className="hover:text-[#1CB0F6] transition-colors">개발 참고</a></li>
                <li><a href="#" className="hover:text-[#1CB0F6] transition-colors">기술 문서</a></li>
              </ul>
            </div>

            {/* Policy (3 cols) */}
            <div className="md:col-span-3">
              <h3 className="font-semibold text-gray-900 mb-4">Policy</h3>
              <ul className="space-y-3 text-sm text-gray-600">
                <li><a href="#" className="hover:text-[#1CB0F6] transition-colors">개인정보 처리방침</a></li>
                <li><a href="#" className="hover:text-[#1CB0F6] transition-colors">서비스 이용약관</a></li>
                <li><a href="#" className="hover:text-[#1CB0F6] transition-colors">오픈소스 라이선스</a></li>
              </ul>
            </div>
          </div>
          
          <div className="border-t border-[#1CB0F6]/10 pt-8 flex flex-col md:flex-row justify-between items-center gap-4 text-sm text-gray-500">
            <p>Copyright © 2024 Trenda. All rights reserved.</p>
            <div className="flex gap-6">
              <a href="#" className="hover:text-[#1CB0F6]">Privacy</a>
              <a href="#" className="hover:text-[#1CB0F6]">Terms</a>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
}
